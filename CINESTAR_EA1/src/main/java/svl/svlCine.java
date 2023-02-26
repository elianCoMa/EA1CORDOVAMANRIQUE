package svl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


public class svlCine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public svlCine() {
        super();

    }

    protected void proccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text-html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		dao.CineDAO daoCine = new dao.CineDAO();
		
		Object idCine= request.getParameter("idCine");
		if(idCine == null) {
			String[][] mCines = daoCine.getVerCines();
			session.setAttribute("id", mCines== null ? null : "3");
			session.setAttribute("mCines", mCines);
		} else {
			String[] aCine = daoCine.getCine(idCine);
			session.setAttribute("id", aCine== null ? null : "4");
			session.setAttribute("aCine",aCine);
			session.setAttribute("mTarifas", daoCine.getCineTarifas(idCine));
			session.setAttribute("mPeliculas", daoCine.getCinePeliculas(idCine));
		}
			
			
		response.sendRedirect("index.jsp");	

	}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccesRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		proccesRequest(request, response);
	}

}
