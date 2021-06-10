
public class CustomParseJson {

  public static void main(String[] args) {
    String jsontext = "{\"itemStart\":true}[{\"warningCode\":\"ABC-WRN-001\",\"warningMessage\":\"Data not found\"}]{\"itemEnd\":true}";
    String itemStartTrue = getNextResponse(jsontext);
    System.out.println(itemStartTrue);
    String streamResp = jsontext.substring(itemStartTrue.length() + 1);
    String warningResp = getNextResponse(streamResp);
    System.out.println(warningResp);
    //var wrnResJson = JSON.parse(warningResp);
  }

  public static String getNextResponse (String resstr) {
    String rstr = "";
    int st = 0;
    for (int i = 0; i < resstr.length(); i++) {

        rstr = rstr + resstr.charAt(i);

        if ( st == 0 && (resstr.charAt(i) == '{'
        || resstr.charAt(i) == '[')) {
            st = 1;
        }

        if (st == 1 && (resstr.charAt(i) == '}'
        || resstr.charAt(i) == ']')) {
            if ((i+1) < resstr.length()) {
                return rstr;
            }
        }

        if (st == 1 && i >= resstr.length()) {
            return rstr;
        }
    }
    return rstr;
  }

}
