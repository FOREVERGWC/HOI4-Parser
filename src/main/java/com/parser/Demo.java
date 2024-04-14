package com.parser;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONObject;

public class Demo {
    public static void main(String[] args) {
        String inputPath = "static/test.txt";
        String outputPath = System.getProperty("user.dir") + "\\static\\demo.txt";

        ParadoxParser paradoxParser = new ParadoxParser();
        JSONObject object = paradoxParser.parse(ResourceUtil.readUtf8Str(inputPath));
        String content = paradoxParser.generate(object);
        String parserStr = paradoxParser.getStr(content);
        FileUtil.writeUtf8String(parserStr, outputPath);

//        // 工具库测试
//        List<File> list = new ArrayList<>();
//        list.add(new File("src/main/resources/static/characters/ABK.txt"));
//        list.add(new File("src/main/resources/static/characters/AFA.txt"));
//        Hoi4Util.CharacterUtil(list);
    }
}
