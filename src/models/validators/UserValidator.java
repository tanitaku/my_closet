package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String user_name_error = validateCode(u.getUser_name(), codeDuplicateCheckFlag);
        if(!user_name_error.equals("")) {
            errors.add(user_name_error);
        }

        String name_error = validateName(u.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // ユーザーネーム
    private static String validateCode(String user_name, Boolean codeDuplicateCheckFlag) {
        // 必須入力チェック
        if(user_name == null || user_name.equals("")) {
            return "ユーザー名を入力してください。";
        }

        // すでに登録されているユーザー名との重複チェック
        if(codeDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long user_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("user_name", user_name)
                                             .getSingleResult();
            em.close();
            if(user_count > 0) {
                return "入力されたユーザー名の情報はすでに存在しています。";
            }
        }

        return "";
    }

    // 氏名の必須入力チェック
    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return "氏名を入力してください。";
        }

        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        // パスワードを変更する場合のみ実行
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}