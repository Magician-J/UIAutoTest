package test_framework;

import java.util.HashMap;
import java.util.List;

/**
 * @author jiaoyl
 * @date 2020/6/17 23:01
 */
public class UIAuto {
    public String name="";
    public String description="";
    //步骤是一个列表，包含一批操作。
    public List<HashMap<String, Object>> steps;
}
