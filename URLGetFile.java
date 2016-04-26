import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class URLGetFile extends Applet{

	TextField txtUrl = new TextField("http://www.baidu.com");
	Button btn = new Button("start");
	TextArea textContent = new TextArea("Content");

	public void init(){
		
		add(txtUrl);
		add(btn);
		add(textContent);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String strurl = txtUrl.getText();
				try{
					URL url = new URL(strurl);
					textContent.setText(" ");
					getPageContent(url);
				}catch(MalformedURLException ex){
					ex.printStackTrace();
				}
			}
		});
		
	}
	
	public void getPageContent(URL url){
		
		InputStream filecon = null;
		BufferedReader filedata = null;
		String line;
		try{
			filecon = url.openStream();
			filedata = new BufferedReader(new InputStreamReader(filecon));
			while((line = filedata.readLine()) != null){
				textContent.append(line + "\n");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		
		Frame frame = new Frame("URL Test");
		Applet ap = new URLGetFile();
		ap.init();
		frame.add(ap);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.setSize(400,300);
		ap.start();
		
	}
	
}
