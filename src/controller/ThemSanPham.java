package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Manufacturer;
import model.Product;

@WebServlet("/SanPhamDangKinhDoanh/ThemSanPham")
public class ThemSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemSanPham() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Manufacturer manuf = new Manufacturer();
		manuf.setId(Integer.parseInt(request.getParameter("manufId")));
		request.setAttribute("manuf", manuf);
		
		Boolean insertProductError = (Boolean)getServletContext().getAttribute("insertProductError");
		if (insertProductError != null) {
			getServletContext().removeAttribute("insertProductError");
		}
		else {
			insertProductError = false;
		}
		request.setAttribute("insertProductError", insertProductError);
		
		request.getRequestDispatcher("/WEB-INF/ThemSanPham.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Product product = new Product();
		
		String productName = request.getParameter("productName");
		product.setName(productName);
		
		String productPrice = request.getParameter("productPrice");
		if (productPrice.length() == 0) {
			product.setPrice(null);
		}
		else {
			product.setPrice(Integer.parseInt(productPrice));
		}
		
		String productImageBase64 = request.getParameter("productImageBase64");
		if (productImageBase64.length() == 0) {
			product.setImageBase64(null);
		}
		else {
			product.setImageBase64(productImageBase64.replace("data:image/jpeg;base64,", ""));
		}
		
		String productScreenSize = request.getParameter("productScreenSize");
		if (productScreenSize.length() == 0) {
			product.setScreenSize(null);
		}
		else {
			product.setScreenSize(Float.parseFloat(productScreenSize));
		}
		
		String productScreenTechnology = request.getParameter("productScreenTechnology");
		if (productScreenTechnology.length() == 0) {
			product.setScreenTechnology(null);
		}
		else {
			product.setScreenTechnology(productScreenTechnology);
		}
		
		String productOs = request.getParameter("productOs");
		if (productOs.length() == 0) {
			product.setOs(null);
		}
		else {
			product.setOs(productOs);
		}
		
		String productFrontCamera = request.getParameter("productFrontCamera");
		if (productFrontCamera.length() == 0) {
			product.setFrontCamera(null);
		}
		else {
			product.setFrontCamera(productFrontCamera);
		}
		
		String productPosteriorCamera = request.getParameter("productPosteriorCamera");
		if (productPosteriorCamera.length() == 0) {
			product.setPosteriorCamera(null);
		}
		else {
			product.setPosteriorCamera(productPosteriorCamera);
		}
		
		String productCpu = request.getParameter("productCpu");
		if (productCpu.length() == 0) {
			product.setCpu(null);
		}
		else {
			product.setCpu(productCpu);
		}
		
		String productRam = request.getParameter("productRam");
		if (productRam.length() == 0) {
			product.setRam(null);
		}
		else {
			product.setRam(productRam);
		}
		
		String productInternalMemory = request.getParameter("productInternalMemory");
		if (productInternalMemory.length() == 0) {
			product.setInternalMemory(null);
		}
		else {
			product.setInternalMemory(productInternalMemory);
		}
		
		String productMemoryStick = request.getParameter("productMemoryStick");
		if (productMemoryStick.length() == 0) {
			product.setMemoryStick(null);
		}
		else {
			product.setMemoryStick(productMemoryStick);
		}
		
		String productSim = request.getParameter("productSim");
		if (productSim.length() == 0) {
			product.setSim(null);
		}
		else {
			product.setSim(productSim);
		}
		
		String productPin = request.getParameter("productPin");
		if (productPin.length() == 0) {
			product.setPin(null);
		}
		else {
			product.setPin(productPin);
		}
		
		List<String> productColors = new ArrayList<String>();
		String[] colors = request.getParameter("productColors").split(",");
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colors[i].trim();
			productColors.add(colors[i]);
		}
		product.setColors(productColors);
		
		Manufacturer manuf = new Manufacturer(Integer.parseInt(request.getParameter("manufId")));
		product.setManufacturer(manuf);
		
		if (request.getParameter("waterproof") != null) {
			product.setWaterproof(true);
		}
		else {
			product.setWaterproof(false);
		}
		
		if (request.getParameter("dualCamera") != null) {
			product.setDualCamera(true);
		}
		else {
			product.setDualCamera(false);
		}
		
		if (request.getParameter("fastCharging") != null) {
			product.setFastCharging(true);
		}
		else {
			product.setFastCharging(false);
		}
		
		if (request.getParameter("faceSecurity") != null) {
			product.setFaceSecurity(true);
		}
		else {
			product.setFaceSecurity(false);
		}
		
		if (request.getParameter("fingerprintSecurity") != null) {
			product.setFingerprintSecurity(true);
		}
		else {
			product.setFingerprintSecurity(false);
		}
		
		boolean insertProductError = ProductDAO.insertProduct(product);
		if (insertProductError == true) {
			getServletContext().setAttribute("insertProductError", insertProductError);
			response.sendRedirect(request.getContextPath() + "/SanPhamDangKinhDoanh/ThemSanPham?manufId=" + manuf.getId());
		}
		else {
			response.sendRedirect(request.getContextPath() + "/SanPhamDangKinhDoanh/HangSanPham?manufId=" + manuf.getId());
		}
	}
}
