package test_framework;

import java.util.HashMap;
import java.util.List;

/**
 * @author jiaoyl
 * @date 2020/6/23 23:18
 */
public class PageObjectModel {
    //
    public String name;
    //对应main_page.yaml里的结构
    public HashMap<String, List<HashMap<String, Object>>> methods;
}
