package bzh.guillaume;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp){
		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		try{
			doSlowWrite(resp);
		}catch(Exception e){ // prevent the IOException to bubble up
			log(e.getMessage());
		}
	}

	private void doSlowWrite(HttpServletResponse resp) throws IOException, InterruptedException{
		ServletOutputStream outputStream = resp.getOutputStream();
		for(int i = 0; i < 10; i++){
			outputStream.println("hey " + i);
			outputStream.flush(); // throw an java.io.EOFException: The client aborted the connection if request aborted
			Thread.sleep(1000);
		}
	}

}
