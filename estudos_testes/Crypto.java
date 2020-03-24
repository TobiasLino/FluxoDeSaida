import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Crypto {
	public static byte[] encrypt(MessageDigest alg, String senha) throws UnsupportedEncodingException {
	    return alg.digest(senha.getBytes("UTF-8"));
	  }
	  public static String decrypt(byte messageDigest[]) {
	    StringBuilder hex = new StringBuilder();
	    for (byte b : messageDigest) {
	      hex.append((String.format("%02x", 0xFF & b)));
	    }
	    return hex.toString();
	  }
	  public static void main(String[] args) {
	    MessageDigest alg;
		try {
			alg = MessageDigest.getInstance("SHA-256");
		    byte messageDigest[] = alg.digest("ambar".getBytes("UTF-8"));
		    do {
		      Scanner scan = new Scanner(System.in);
		      String pass = scan.nextLine();
	
		      MessageDigest v = MessageDigest.getInstance("SHA-256");
		      byte messageDigest2[] = encrypt(v, pass);
		      
		      if (decrypt(messageDigest).equals(decrypt(messageDigest2))) {
		        System.out.println("São iguais");
		        System.out.println(messageDigest2);
		        return;
		      } else {
		        System.out.println("Erro");
		      }
		    } while (true);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	  }
}

