package com.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.parser.ParadoxParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hoi4Util {
    private static List<Map<String, String>> findNames(JSONObject json, List<Map<String, String>> names, String path) {
        json.forEach((key, value) -> {
            if (value instanceof JSONObject) {
                findNames((JSONObject) value, names, path + key + "|");
            } else if ("name".equals(key)) {
                String allPath = path + "name";
                if (ReUtil.isMatch("^\"(.*)\"$", String.valueOf(value))) {
                    String name = ReUtil.getGroup1("^\"(.*)\"$", String.valueOf(value));
                    json.set(key, name);
                    Map<String, String> map = new HashMap<>();
                    map.put(key, name);
                    map.put("key", ReUtil.getGroup1("^characters\\|(.*)\\|name$", allPath));
                    names.add(map);
                }
            }
        });
        return names;
    }

    public static void CharacterUtil(List<File> files) {
        FileUtil.appendUtf8String("\uFEFFl_english:\n", System.getProperty("user.dir") + "\\static\\characters_l_english.yml");
        for (File file : files) {
            ParadoxParser parser = new ParadoxParser();
            JSONObject object = parser.parse(FileUtil.readUtf8String(file));
            List<Map<String, String>> names = findNames(object, new ArrayList<>(), "");

            names.forEach(name -> {
                String line = StrUtil.format(" {}:0 \"{}\"\n", name.get("key"), name.get("name"));
                FileUtil.appendUtf8String(line, System.getProperty("user.dir") + "\\static\\characters_l_english.yml");
            });

//            FileUtil.writeUtf8String(parser.generate(object), file);
        }
        // 遍历读入文件内容
        // 导出硬编码人名、图像路径
        // 写入文件
        // 导出本地化文件和接口文件
    }
}
