package com.parser;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONObject;

public class Demo {
    public static void main(String[] args) {
        String inputPath = "static/test.txt";
        String outputPath = System.getProperty("user.dir") + "\\static\\demo.txt";

        ParadoxParser paradoxParser = new ParadoxParser();
        // 读入游戏文件
        JSONObject object = paradoxParser.parse(ResourceUtil.readUtf8Str(inputPath));
        // 对JSON进行处理
        // 写入文件
        FileUtil.writeUtf8String(paradoxParser.generate(object), outputPath);
    }
}
