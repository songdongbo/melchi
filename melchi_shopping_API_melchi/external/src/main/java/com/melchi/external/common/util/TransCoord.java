package com.melchi.external.common.util;

public class TransCoord {
	private double BASE_TM_LON = 127;
	private double BASE_TM_LAT = 38;
	private double BASE_KTM_LON = 128;
	private double BASE_KTM_LAT = 38;
	private double m_AW = 6378137;
	//this.m_FW = 0.0033528106647474805;
	private double m_FW = 0.0033550106647474805;
	private double m_AB = 6377397.1550000003;
	private double m_FB = 0.0033427731799399794;
	private double m_OKGTM = 0.99990000000000001;
	private double m_TX = 145.77799999999999;
	private double m_TY = -503.702;
	private double m_TZ = -684.66999999999996;
	private double m_x0 = 500000;
	private double m_y0 = 200000;
	private double m_x1 = 600000;
	private double m_y1 = 400000;
	private double m_dx = 0.0;
	private double m_dy = 0.0;
	private double m_dz = 0.0;
	private double m_omega = 0.0;
	private double m_phi = 0.0;
	private double m_kappa = 0.0;
	private double m_ds = 0.0;
	private double m_imode = 0.0;
	private double degrad = 0.0;
	
	
	public double getDegrad() {
		return degrad;
	}
	public void setDegrad(double degrad) {
		this.degrad = degrad;
	}
	public double getBASE_TM_LON() {
		return BASE_TM_LON;
	}
	public void setBASE_TM_LON(double bASETMLON) {
		BASE_TM_LON = bASETMLON;
	}
	public double getBASE_TM_LAT() {
		return BASE_TM_LAT;
	}
	public void setBASE_TM_LAT(double bASETMLAT) {
		BASE_TM_LAT = bASETMLAT;
	}
	public double getBASE_KTM_LON() {
		return BASE_KTM_LON;
	}
	public void setBASE_KTM_LON(double bASEKTMLON) {
		BASE_KTM_LON = bASEKTMLON;
	}
	public double getBASE_KTM_LAT() {
		return BASE_KTM_LAT;
	}
	public void setBASE_KTM_LAT(double bASEKTMLAT) {
		BASE_KTM_LAT = bASEKTMLAT;
	}
	public double getM_AW() {
		return m_AW;
	}
	public void setM_AW(double mAW) {
		m_AW = mAW;
	}
	public double getM_FW() {
		return m_FW;
	}
	public void setM_FW(double mFW) {
		m_FW = mFW;
	}
	public double getM_AB() {
		return m_AB;
	}
	public void setM_AB(double mAB) {
		m_AB = mAB;
	}
	public double getM_FB() {
		return m_FB;
	}
	public void setM_FB(double mFB) {
		m_FB = mFB;
	}
	public double getM_OKGTM() {
		return m_OKGTM;
	}
	public void setM_OKGTM(double mOKGTM) {
		m_OKGTM = mOKGTM;
	}
	public double getM_TX() {
		return m_TX;
	}
	public void setM_TX(double mTX) {
		m_TX = mTX;
	}
	public double getM_TY() {
		return m_TY;
	}
	public void setM_TY(double mTY) {
		m_TY = mTY;
	}
	public double getM_TZ() {
		return m_TZ;
	}
	public void setM_TZ(double mTZ) {
		m_TZ = mTZ;
	}
	public double getM_x0() {
		return m_x0;
	}
	public void setM_x0(double mX0) {
		m_x0 = mX0;
	}
	public double getM_y0() {
		return m_y0;
	}
	public void setM_y0(double mY0) {
		m_y0 = mY0;
	}
	public double getM_x1() {
		return m_x1;
	}
	public void setM_x1(double mX1) {
		m_x1 = mX1;
	}
	public double getM_y1() {
		return m_y1;
	}
	public void setM_y1(double mY1) {
		m_y1 = mY1;
	}
	public double getM_dx() {
		return m_dx;
	}
	public void setM_dx(double mDx) {
		m_dx = mDx;
	}
	public double getM_dy() {
		return m_dy;
	}
	public void setM_dy(double mDy) {
		m_dy = mDy;
	}
	public double getM_dz() {
		return m_dz;
	}
	public void setM_dz(double mDz) {
		m_dz = mDz;
	}
	public double getM_omega() {
		return m_omega;
	}
	public void setM_omega(double mOmega) {
		m_omega = mOmega;
	}
	public double getM_phi() {
		return m_phi;
	}
	public void setM_phi(double mPhi) {
		m_phi = mPhi;
	}
	public double getM_kappa() {
		return m_kappa;
	}
	public void setM_kappa(double mKappa) {
		m_kappa = mKappa;
	}
	public double getM_ds() {
		return m_ds;
	}
	public void setM_ds(double mDs) {
		m_ds = mDs;
	}
	public double getM_imode() {
		return m_imode;
	}
	public void setM_imode(double mImode) {
		m_imode = mImode;
	}
	
	
}
