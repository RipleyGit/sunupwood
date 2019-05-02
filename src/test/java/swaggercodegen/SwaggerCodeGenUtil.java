package swaggercodegen;


import io.swagger.codegen.SwaggerCodegen;


public class SwaggerCodeGenUtil {

    public static void main(String[] args) {
        SwaggerCodegen.main(new String[]{"generate",
                "-i", "src/main/resources/static/swagger/yaml/data.yaml",
                "-l", "spring",
                "-c", "src/main/resources/static/swagger/conf/configData.json",
                "-o", "."});
//
//        SwaggerCodegen.main(new String[]{"generate",
//                "-i", "src/main/resources/static/swagger/yaml/portal.yaml",
//                "-l", "spring",
//                "-c", "src/test/resources/swagger/configPortal.json",
//                "-o", "."});
//
//        SwaggerCodegen.main(new String[]{"generate",
//                "-i", "src/main/resources/static/swagger/yaml/other.yaml",
//                "-l", "spring",
//                "-c", "src/test/resources/swagger/configOther.json",
//                "-o", "."});
//        SwaggerCodegen.main(new String[]{"generate",
//                "-i", "src/main/resources/static/swagger/yaml/data.yaml",
//                "-l", "spring",
//                "-c", "src/test/resources/swagger/configData.json",
//                "-o", "."});


    }

}
