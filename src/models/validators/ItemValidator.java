package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Item;

public class ItemValidator {
    public static List<String> validate(Item i) {
        List<String> errors = new ArrayList<String>();


        // category season color brand
        String category_error = _validateCategory(i.getCategory());
        if(!category_error.equals("")) {
            errors.add(category_error);
        }

        String content_error = _validateContent(i.getContent());
        if(!content_error.equals("")) {
            errors.add(content_error);
        }


        String brand_error = _validateBrand(i.getBrand());
        if(!brand_error.equals("")) {
            errors.add(brand_error);
        }

        String color_error = _validateColor(i.getColor());
        if(!color_error.equals("")) {
            errors.add(color_error);
        }

        String path_error = _validatePath(i.getPath());
        if(!path_error.equals("")) {
            errors.add(path_error);
        }

        String price_error = _validatePrice(i.getPrice());
        if(!price_error.equals("")) {
            errors.add(price_error);
        }

        return errors;
    }

    private static String _validateCategory(String category) {
        if(category == null || category.equals("")) {
            return "カテゴリを入力してください。(トップス、パンツ etc.)";
            }

        return "";
    }

    private static String _validateContent(String content) {
        if(content == null || content.equals("")) {
            return "内容を入力してください。";
            }

        return "";
    }


    private static String _validateBrand(String brand) {
        if(brand == null || brand.equals("")) {
            return "ブランド名を入力してください";
        }

        return "";
    }

    private static String _validateColor(String color) {
        if(color == null || color.equals("")) {
            return "色を入力してください";
            }

        return "";
        }

    private static String _validatePath(String path) {
        if(path == null || path.equals("")) {
            return "画像を選んでください";
            }

        return "";
        }

    private static String _validatePrice(Integer price) {
        if(price == null || price.equals("")) {
            return "価格を入力してください";
        }
        return "";
    }
    }
