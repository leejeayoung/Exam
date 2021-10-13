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
		System.out.print("�Է°�: ");
		
		
		
		try {
			String br_text = br.readLine();
			if(br_text.equals("exit")) 
			{
				System.out.println("�����մϴ�.");
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
		
		if(value != -1) { // : �� ���� �Ǿ� �ִٸ�
			fileName = br_text.substring(0,value); // : ����
			fileName = fileName.trim();
			countString = br_text.substring(value+1); // : ����
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
			}catch (NumberFormatException ne) { // : ���Ŀ� ���ڰ� �ƴϸ�
				System.out.println("�߸��� �����Դϴ�. �ٽ� �Է� ���ּ���. [���� �Ͻ÷��� 'exit'�� �Է����ּ���.]");
				System.out.println(" : ���Ŀ��� ���ڸ� �� �� �ֽ��ϴ�.");
				return false;
			}catch (IOException ie) {
				System.out.println("���� ������ ����.... �ٽ� �Է� ���ּ���. [���� �Ͻ÷��� 'exit'�� �Է����ּ���.]");
				return false;
			}
			return true;
		}
		else { // : �� ���� �Ǿ� ���� �ʴٸ� 
			System.out.println("�߸��� ���� �Դϴ�. �ٽ� �Է� ���ּ���. [���� �Ͻ÷��� 'exit'�� �Է����ּ���.]");
			System.out.println("�Է°� ���� >>> ���� : ����");
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
