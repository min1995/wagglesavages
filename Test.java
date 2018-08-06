import java.util.StringTokenizer;

public class Test {
	
	public static void main(String[] args) {
		SocketTest st = new SocketTest();
		String result = st.runClient("waggle");
		System.out.println("result is ..." + result);
		StringTokenizer strTok1 = new StringTokenizer(result, "$");
		while(strTok1.hasMoreTokens()) {
			StringTokenizer strTok2  = new StringTokenizer(strTok1.nextToken(), "/");
			while(strTok2.hasMoreTokens()) {
				System.out.println(strTok2.nextToken());
			}
		}
	}
	
}

/*


hi
234
23432
2018-08-01 00:00:00
purdue
waggle1
asdf
asdf
2018-07-19 00:00:00
asdf
waggle2
234
23432
2018-08-01 16:33:50
purdue




*/