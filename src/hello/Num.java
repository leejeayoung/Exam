package hello;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Num {
	public boolean start() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("입력값: ");
		
		
		
		try {
			String br_text = br.readLine();
			if(br_text.equals("exit")) 
			{
				System.out.println("감사합니다.");
				System.exit(0);
			}
			boolean result = cut(br_text);
			return result;
		}catch(IOException ie) {
			return false;
		}
		
	}
	public boolean cut(String br_text) {
		String data="";
		String fileName ="";
		String countString="";
		int countInt = 0;
		int value =br_text.indexOf(":");
		
		if(value != -1) { // : 가 포함 되어 있다면
			fileName = br_text.substring(0,value); // : 이전
			fileName = fileName.trim();
			countString = br_text.substring(value+1); // : 이후
			countString.trim();
			
			try { 
				countInt = Integer.parseInt(countString);
				FileOutputStream output = new FileOutputStream("FileSave/"+fileName+".txt");
				for(int i = 0;i<countInt;i++) {
					for(int j = 1;j<=countInt;j++) {
						data = j+" ";
						output.write(data.getBytes());
					}
					data = "\r\n";
					output.write(data.getBytes());
				}
				output.close();
				outcome(fileName);
			}catch (NumberFormatException ne) { // : 이후에 숫자가 아니면
				System.out.println("잘못된 형식입니다. 다시 입력 해주세요. [종료 하시려면 'exit'를 입력해주세요.]");
				System.out.println(" : 이후에는 숫자만 올 수 있습니다.");
				return false;
			}catch (IOException ie) {
				System.out.println("파일 생성중 오류.... 다시 입력 해주세요. [종료 하시려면 'exit'를 입력해주세요.]");
				return false;
			}
			return true;
		}
		else { // : 가 포함 되어 있지 않다면 
			System.out.println("잘못된 형식 입니다. 다시 입력 해주세요. [종료 하시려면 'exit'를 입력해주세요.]");
			System.out.println("입력값 형식 >>> 문자 : 숫자");
			return false;
		}
		
	}
	
	public void outcome(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("FileSave/"+fileName+".txt"));
			while(true) {
				String line = br.readLine();
				if(line==null) break;
				System.out.println(line);
			}
			br.close();
			fileDelete(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ie ) {
		}
	}
	
	public void fileDelete(String fileName) {
		File file = new File("FileSave/"+fileName+".txt");
		
		if(file.exists()){ 
			file.delete();
		}
	}
	public static void main(String[] args) {
		
		Num num = new Num();

		while(true) {
			boolean result = num.start();
			if(result == true) {
				break;
			}else {
				num.start();
			}
		}
		
	}
}
