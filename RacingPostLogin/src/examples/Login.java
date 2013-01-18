package examples;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class Login {

	public static void main(String[] args) throws Exception {
		
		String username = "ricardop";
		String password = "";

		// Create and initialize WebClient object
	    WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
	    webClient.setThrowExceptionOnScriptError(false);
	    webClient.setRefreshHandler(new RefreshHandler() {
			public void handleRefresh(Page page, URL url, int arg) throws IOException {
				System.out.println("handleRefresh");
			}

	    });

	    // visit Racing Post login page and get the Form object
	    HtmlPage page = (HtmlPage) webClient.getPage("https://reg.racingpost.com/modal_dialog/login.sd?protoSecure=0");
	    HtmlForm form = page.getFormByName("loginFrm");

	    // Enter login and passwd
	    form.getInputByName("in_un").setValueAttribute(username);
	    form.getInputByName("in_pw").setValueAttribute(password);

	    // add submit button and click
	    HtmlButton submitButton = (HtmlButton)page.createElement("button");
	    submitButton.setAttribute("type", "submit");
	    form.appendChild(submitButton);
	    page = submitButton.click();
	    
	    //go to new page
	    page = (HtmlPage) webClient.getPage("http://www.racingpost.com/horses/result_home.sd?race_id=545427");  

	    //show output
	    System.out.println(page.asXml());	   	     	    

	}
}
