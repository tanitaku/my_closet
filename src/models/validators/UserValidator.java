package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean userNameDuplicateCheckFlag, Boolean passwordCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String user_name_error = validateUserName(u.getUser_name(), userNameDuplicateCheckFlag);
        if(!user_name_error.equals("")) {
            errors.add(user_name_error);
        }

        String email_error = validateEmail(u.getEmail());
        if(!email_error.equals("")) {
            errors.add(email_error);
        }

        String password_error = validatePassword(u.getPassword(), passwordCheckFlag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // ユーザーネーム
    private static String validateUserName(String user_name, Boolean userNameDuplicateCheckFlag) {
        // 必須入力チェック
        if(user_name == null || user_name.equals("")) {
            return "ユーザー名を入力してください。";
        }

        // すでに登録されているユーザー名との重複チェック
        if(userNameDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long user_count = (long)em.createNamedQuery("checkRegisteredUserName", Long.class)
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
    private static String validateEmail(String email) {
        if(email == null || email.equals("")) {
            return "メールアドレスを入力してください。";
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