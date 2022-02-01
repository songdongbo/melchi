package com.melchi.external.common.util;

import java.io.Serializable;

/**
 * @import java.io.Serializable;
ions, Contents Dev. Team) - 2010.07.15
 * 1. Converting coordinates type between WGS84 and KTM(TM128).
 * 2. Calulating Distance using by WGS84 or KTM coordinates.
 */
public class CoordsUtil implements Serializable {
	private static final long serialVersionUID = -6198295053879874534L;
	private static TransCoord tc = null;
    
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static CoordsUtil getInstance()
        throws Exception
    {
        if(conv == null)
            conv = new CoordsUtil();
        return conv;
    }

	/**
	 * 
	 * @throws Exception
	 */
	CoordsUtil()
        throws Exception
    {
    	initKTMConv();
    }

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
    public static double[] conversion_WGS_to_KTM (double x, double y) throws Exception {
        if("".equals(x) || "".equals(y) || Double.toString(x) == null || Double.toString(y) == null)
        {
            return defaultPoint;
        } else
        {
            points = doConversion(x, y, "KTM");
            return points;
        }
    }

    /**
     * 
     * @param x
     * @param y
     * @return
     * @throws Exception
     */
    public static double[] conversion_KTM_to_WGS (double x, double y) throws Exception {
        if("".equals(x) || "".equals(y) || Double.toString(x) == null || Double.toString(y) == null)
        {
            return defaultPoint;
        } else
        {
            points = doConversion(x, y, "WGS84");
            changeXY(points);
            return points;
        }
    }
    
    /**
     * conversion_KTM_to_Bessel
     * - bessel 로 변환시 TM2GP 값에 36000를 곱해준다. 
     * @param x, y
     * @return double x, y
     * @throws Exception
     */
    public static double[] conversion_KTM_to_Bessel (double x, double y) throws Exception {
        if("".equals(x) || "".equals(y) || Double.toString(x) == null || Double.toString(y) == null)
        {
            return defaultPoint;
        } else
        {
            points = doConversion(x, y, "KTM2Bessel");
            return points;
        }
    }    
    /**
     * conversion_Bessel_to_KTM
     * - 입력받는 x, y 좌표값에 36000를 나누기 한다.
     * @param x, y
     * @return double x, y
     * @throws Exception
     */
    public static double[] conversion_Bessel_to_KTM (double x, double y) throws Exception {
        if("".equals(x) || "".equals(y) || Double.toString(x) == null || Double.toString(y) == null)
        {
            return defaultPoint;
        } else
        {
            points = doConversion(x, y, "Bessel2KTM");
            return points;
        }
    }    
    
    /**
     * 
     * @param pt1
     * @param pt2
     * @return
     */
    public static double measure_WGS_Distance (double[] pt1, double[] pt2) {
		double lat1 = D2R(pt1[1]);
		double lon1 = D2R(pt1[0]);
		double lat2 = D2R(pt2[1]);
		double lon2 = D2R(pt2[0]);

		double longitude = lon2 - lon1;
		double latitude = lat2 - lat1;

		double a = Math.pow(Math.sin(latitude / 2.0), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(longitude / 2.0), 2);
		double dist = (6376.5 * 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a))) * 1000;
		return Math.round(dist);
	}
    
    /**
     * 
     * @param pt1
     * @param pt2
     * @return
     * @throws Exception
     */
    public static double measure_KTM_Distance (double[] pt1, double[] pt2) throws Exception {
		pt1 = conversion_KTM_to_WGS(pt1[0],pt1[1]);
		pt2 = conversion_KTM_to_WGS(pt2[0],pt2[1]);
		return measure_WGS_Distance(pt1, pt2);
	}    
    
    /**
     * 
     * @throws Exception
     */
    private static void initKTMConv ()
        throws Exception
    {	
    	if(tc == null)
    		tc = new TransCoord();
    }
    
    /**
     * 
     * @param degree
     * @return
     */
    public static double D2R (double degree) {
		return degree* Math.PI / 180.0;
	}
    
    /**
     * 
     * @param xy
     * @return
     */
    private static double[] changeXY (double[] xy){
		double tmp = xy[0];
		xy[0] = xy[1];
		xy[1] = tmp;
		return xy;
	}
    
    /**
     * 
     * @param x
     * @param y
     * @param type
     * @return
     * @throws Exception
     */
    private static double[] doConversion (double x, double y, String type) throws Exception {
	    if(type.equalsIgnoreCase("WGS84")) {
	        double[] tm = convertKTM2TM(x, y);
	        double[] wgs = convertTM2WGS(tm[0], tm[1]);
	        return wgs;
	    } else if(type.equalsIgnoreCase("KTM2Bessel")) {
	    	 double[] tm = convertKTM2TM(x, y);
		     double[] bessel = convertTM2Bessel(tm[0], tm[1]);
		     return bessel;
	    } else if(type.equalsIgnoreCase("Bessel2KTM")) {
	    	 double[] tm = convertBessel2TM(x/36000, y/36000);
		     double[] bessel = convertTM2KTM(tm[0], tm[1]);
		     return bessel;     
	    } else {
	    	double[] TM = convertWGS2TM(x, y);  
	    	double[] ktm = convertTM2KTM(TM[0], TM[1]);
	    	return ktm;
	    }
	}

    /**
     * 
     * @param dx
     * @param dy
     * @param dz
     * @param omega
     * @param phi
     * @param kappa
     * @param ds
     * @param imode
     */
    private static void setParameter (double dx, double dy, double dz, double omega, double phi, double kappa, double ds, double imode){
    	tc.setDegrad(0.0);
    	tc.setDegrad(Math.atan(1.0) / 45);
    	tc.setM_dx(dx);
    	tc.setM_dy(dy);
    	tc.setM_dz(dz);
    	tc.setM_omega((omega / 3600) * tc.getDegrad());
    	tc.setM_phi((phi / 3600) * tc.getDegrad());
    	tc.setM_kappa((kappa / 3600) * tc.getDegrad());
    	tc.setM_ds(ds * 9.9999999999999995E-007);
    	tc.setM_imode(imode);
    }
    
    /**
     *  
     * @param tmx
     * @param tmy
     * @return
     */
    private static double[] convertKTM2TM (double tmx, double tmy){
    	double[] TM = TM2GP(tmy, tmx, tc.getM_AB(), tc.getM_FB(), tc.getM_x1(), tc.getM_y1(), tc.getM_OKGTM(), tc.getBASE_KTM_LAT(), tc.getBASE_KTM_LON());
    	TM = GP2TM(TM[0], TM[1], tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(), tc.getBASE_TM_LON() + 0.0028902777777777776);
    	TM = changeXY(TM);
    	TM[0] = Math.round(TM[0]);
    	TM[1] = Math.round(TM[1]);
    	return TM;
    }
    
    /**
     * 
     * @param tmx
     * @param tmy
     * @return
     */
    private static double[] convertTM2WGS (double tmx, double tmy){
    	setParameter(tc.getM_TX(), tc.getM_TY(), tc.getM_TZ(), tc.getM_omega(), tc.getM_phi(), tc.getM_kappa(), tc.getM_ds(), 1);
    	double[] GP = TM2GP(tmy, tmx, tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(), tc.getBASE_TM_LON() + 0.0028902777777777776);
    	return changeXY(GP2WGP(GP[0], GP[1], 0.0, tc.getM_AB(), tc.getM_FB()));
    }
    
    /**
     * convertBESSEL2TM
     * @param double tmx, double tmy
     * @return double[] TM
     */
    private static double[] convertBessel2TM (double tmx, double tmy){
    	double[] TM = GP2TM(tmy, tmx, tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(), tc.getBASE_TM_LON() + 0.0028902777777777776);
    	TM = changeXY(TM);
    	TM[0] = Math.round(TM[0]);
    	TM[1] = Math.round(TM[1]);
    	return TM;
    }
    
    
    private static double[] convertTM2Bessel(double tmx, double tmy){
    	double[] BESSEL = TM2GP(tmy, tmx, tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(), tc.getBASE_TM_LON() + 0.0028902777777777776);
    	BESSEL = changeXY(BESSEL);
    	BESSEL[0] = Math.round(BESSEL[0] * 36000);
   		BESSEL[1] = Math.round(BESSEL[1] * 36000);	
     	return BESSEL;
    }
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @param hu
     * @param a1
     * @param f
     * @return
     */
    private static double[] GP2WGP (double latitude, double longitude, double hu, double a1, double f){
    	double[] CTR = GP2CTR(latitude, longitude, hu, a1, f);
    	CTR = InverseMolod(CTR[0], CTR[1], CTR[2]);
    	return WCTR2WGP(CTR[0], CTR[1], CTR[2]);
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @return
     */
    private static double[] WCTR2WGP (double x, double y, double z){
    	return CTR2GP(x, y, z, tc.getM_AW(), tc.getM_FW());
    }
    
    /**
     * 
     * @param xb
     * @param yb
     * @param zb
     * @return
     */
    private static double[] InverseMolod (double xb, double yb, double zb){
		double xt = 0.0;
		double yt = 0.0;
		double zt = 0.0;
		xt = (xb - tc.getM_TX()) * 1.0;
		yt = (yb - tc.getM_TY()) * 1.0;
		zt = (zb - tc.getM_TZ()) * 1.0;
		double[] xyz_w={
			1.0 * ((xt - 0.0 * yt) + 0.0 * zt),
			1.0 * ((0.0 * xt + yt) - 0.0 * zt), 
			1.0 * (-0 * xt + 0.0 * yt + zt)
		};
		return xyz_w;
	}

    /**
     * 
     * @param xn
     * @param ye
     * @param a1
     * @param f1
     * @param x0
     * @param y0
     * @param ok
     * @param base1
     * @param base2
     * @return
     */
    private static double[] TM2GP (double xn, double ye, double a1, double f1, double x0, double y0, double ok, double base1, double base2){
    	double sphi = 0.0;
    	double slam = 0.0;
    	double sphi0 = 0.0;
    	double slam0 = 0.0;
    	double f = 0.0;
    	double fe = 0.0;
    	double degrad = 0.0;
    	double recf = 0.0;
    	double b = 0.0;
    	double es = 0.0;
    	double ebs = 0.0;
    	double tn = 0.0;
    	double ap = 0.0;
    	double bp = 0.0;
    	double cp = 0.0;
    	double dp = 0.0;
    	double ep = 0.0;
    	double dLam = 0.0;
    	double s = 0.0;
    	double c = 0.0;
    	double t = 0.0;
    	double eta = 0.0;
    	double sn = 0.0;
    	double tmd = 0.0;
    	double tmd1 = 0.0;
    	double nfn = 0.0;
    	double xn1 = 0.0;
    	double t10 = 0.0;
    	double t11 = 0.0;
    	double t12 = 0.0;
    	double t13 = 0.0;
    	double t14 = 0.0;
    	double t15 = 0.0;
    	double t16 = 0.0;
    	double t17 = 0.0;
    	double de = 0.0;
    	double sr = 0.0;
    	double ftphi = 0.0;
    	f = f1;
    	if(f > 1.0)
    		f = 1.0 / f;
    	fe = y0;
    	degrad = Math.atan(1.0) / 45;
    	sphi0 = base1 * degrad;
    	slam0 = base2 * degrad;
    	recf = 1.0 / f;
    	b = (a1 * (recf - 1.0)) / recf;
    	es = (Math.pow(a1, 2) - Math.pow(b, 2)) / Math.pow(a1, 2);
    	ebs = (Math.pow(a1, 2) - Math.pow(b, 2)) / Math.pow(b, 2);
    	tn = (a1 - b) / (a1 + b);
    	ap = a1 * ((1.0 - tn) + (5 * (Math.pow(tn, 2) - Math.pow(tn, 3))) / 4 + (81 * (Math.pow(tn, 4) - Math.pow(tn, 5))) / 64);
    	bp = (3 * a1 * ((tn - Math.pow(tn, 2)) + (7 * (Math.pow(tn, 3) - Math.pow(tn, 4))) / 8 + (55 * Math.pow(tn, 5)) / 64)) / 2;
    	cp = (15 * a1 * ((Math.pow(tn, 2) - Math.pow(tn, 3)) + (3 * (Math.pow(tn, 4) - Math.pow(tn, 5))) / 4)) / 16;
    	dp = (35 * a1 * ((Math.pow(tn, 3) - Math.pow(tn, 4)) + (11 * Math.pow(tn, 5)) / 16)) / 48;
    	ep = (315 * a1 * (Math.pow(tn, 4) - Math.pow(tn, 5))) / 512;
    	tmd1 = (((ap * sphi0 - bp * Math.sin(2 * sphi0)) + cp * Math.sin(4 * sphi0)) - dp * Math.sin(6 * sphi0)) + ep * Math.sin(8 * sphi0);
    	nfn = tmd1 * ok;
    	xn1 = (xn + nfn) - x0;
    	tmd = xn1 / ok;
    	sr = (a1 * (1.0 - es)) / Math.pow(Math.sqrt(1.0 - es * Math.pow(Math.sin(0.0), 2)), 3);
    	ftphi = tmd / sr;    	
    	for(int i = 1; i <= 5; i++)
    	{
    		t10 = (((ap * ftphi - bp * Math.sin(2 * ftphi)) + cp * Math.sin(4 * ftphi)) - dp * Math.sin(6 * ftphi)) + ep * Math.sin(8 * ftphi);
    		sr = (a1 * (1.0 - es)) / Math.pow(Math.sqrt(1.0 - es * Math.pow(Math.sin(ftphi), 2)), 3);
    		ftphi += (tmd - t10) / sr;
    	}

    	sr = (a1 * (1.0 - es)) / Math.pow(Math.sqrt(1.0 - es * Math.pow(Math.sin(ftphi), 2)), 3);
    	sn = a1 / Math.sqrt(1.0 - es * Math.pow(Math.sin(ftphi), 2));
    	s = Math.sin(ftphi);
    	c = Math.cos(ftphi);
    	t = s / c;
    	eta = ebs * Math.pow(c, 2);
    	de = ye - fe;
    	t10 = t / (2 * sr * sn * Math.pow(ok, 2));
    	t11 = (t * ((5 + 3 * Math.pow(t, 2) + eta) - 4 * Math.pow(eta, 2) - 9 * Math.pow(t, 2) * eta)) / (24 * sr * Math.pow(sn, 3) * Math.pow(ok, 4));
    	t12 = (t * ((((((61 + 90 * Math.pow(t, 2) + 46 * eta + 45 * Math.pow(t, 4)) - 252 * Math.pow(t, 2) * eta - 3 * Math.pow(eta, 2)) + 100 * Math.pow(eta, 3)) - 66 * Math.pow(t, 2) * Math.pow(eta, 2) - 90 * Math.pow(t, 4) * eta) + 88 * Math.pow(eta, 4) + 225 * Math.pow(t, 4) * Math.pow(eta, 2) + 84 * Math.pow(t, 2) * Math.pow(eta, 3)) - 192 * Math.pow(t, 2) * Math.pow(eta, 4))) / (720 * sr * Math.pow(sn, 5) * Math.pow(ok, 6));
    	t13 = (t * (1385 + 3633 * Math.pow(t, 2) + 4095 * Math.pow(t, 4) + 1575 * Math.pow(t, 6))) / (40320 * sr * Math.pow(sn, 7) * Math.pow(ok, 8));
    	sphi = (((ftphi - Math.pow(de, 2) * t10) + Math.pow(de, 4) * t11) - Math.pow(de, 6) * t12) + Math.pow(de, 8) * t13;
    	t14 = 1.0 / (sn * c * ok);
    	t15 = (1.0 + 2 * Math.pow(t, 2) + eta) / (6 * Math.pow(sn, 3) * c * Math.pow(ok, 3));
    	t16 = (((((5 + 6 * eta + 28 * Math.pow(t, 2)) - 3 * Math.pow(eta, 2)) + 8 * Math.pow(t, 2) * eta + 24 * Math.pow(t, 4)) - 4 * Math.pow(eta, 3)) + 4 * Math.pow(t, 2) * Math.pow(eta, 2) + 24 * Math.pow(t, 2) * Math.pow(eta, 3)) / (120 * Math.pow(sn, 5) * c * Math.pow(ok, 5));
    	t17 = (61 + 662 * Math.pow(t, 2) + 1320 * Math.pow(t, 4) + 720 * Math.pow(t, 6)) / (5040 * Math.pow(sn, 7) * c * Math.pow(ok, 7));
    	dLam = ((de * t14 - Math.pow(de, 3) * t15) + Math.pow(de, 5) * t16) - Math.pow(de, 7) * t17;
    	slam = slam0 + dLam;
    	double[] xy = {sphi / degrad, slam / degrad};
    	return xy;
    }
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @param a1
     * @param f1
     * @param x0
     * @param y0
     * @param ok
     * @param base1
     * @param base2
     * @return
     */
    private static double[] GP2TM (double latitude, double longitude, double a1, double f1, double x0, double y0, double ok, double base1, double base2){
    	double sphi = 0.0;
    	double slam = 0.0;
    	double sphi0 = 0.0;
    	double slam0 = 0.0;
    	double f = 0.0;
    	double fe = 0.0;
    	double degrad = 0.0;
    	double recf = 0.0;
    	double b = 0.0;
    	double es = 0.0;
    	double ebs = 0.0;
    	double tn = 0.0;
    	double ap = 0.0;
    	double bp = 0.0;
    	double cp = 0.0;
    	double dp = 0.0;
    	double ep = 0.0;
    	double dlam = 0.0;
    	double s = 0.0;
    	double c = 0.0;
    	double t = 0.0;
    	double eta = 0.0;
    	double sn = 0.0;
    	double tmd = 0.0;
    	double tmd1 = 0.0;
    	double nfn = 0.0;
    	double xn1 = 0.0;
    	double t1 = 0.0;
    	double t2 = 0.0;
    	double t3 = 0.0;
    	double t4 = 0.0;
    	double t5 = 0.0;
    	double t6 = 0.0;
    	double t7 = 0.0;
    	double t8 = 0.0;
    	double t9 = 0.0;
    	double phi0 = base1;
    	double lam0 = base2;
    	fe = y0;
    	f = f1;
    	if(f > 1.0)
    		f = 1.0 / f;
    	degrad = Math.atan(1.0) / 45;
    	sphi = latitude * degrad;
    	slam = longitude * degrad;
    	sphi0 = phi0 * degrad;
    	slam0 = lam0 * degrad;
    	recf = 1.0 / f;
    	b = (a1 * (recf - 1.0)) / recf;
    	es = (Math.pow(a1, 2) - Math.pow(b, 2)) / Math.pow(a1, 2);
    	ebs = (Math.pow(a1, 2) - Math.pow(b, 2)) / Math.pow(b, 2);
    	tn = (a1 - b) / (a1 + b);
    	ap = a1 * ((1.0 - tn) + (5 * (Math.pow(tn, 2) - Math.pow(tn, 3))) / 4 + (81 * (Math.pow(tn, 4) - Math.pow(tn, 5))) / 64);
    	bp = (3 * a1 * ((tn - Math.pow(tn, 2)) + (7 * (Math.pow(tn, 3) - Math.pow(tn, 4))) / 8 + (55 * Math.pow(tn, 5)) / 64)) / 2;
    	cp = (15 * a1 * ((Math.pow(tn, 2) - Math.pow(tn, 3)) + (3 * (Math.pow(tn, 4) - Math.pow(tn, 5))) / 4)) / 16;
    	dp = (35 * a1 * ((Math.pow(tn, 3) - Math.pow(tn, 4)) + (11 * Math.pow(tn, 5)) / 16)) / 48;
    	ep = (315 * a1 * (Math.pow(tn, 4) - Math.pow(tn, 5))) / 512;
    	dlam = slam - slam0;
    	tmd1 = (((ap * sphi0 - bp * Math.sin(2 * sphi0)) + cp * Math.sin(4 * sphi0)) - dp * Math.sin(6 * sphi0)) + ep * Math.sin(8 * sphi0);
    	nfn = tmd1 * ok;
    	s = Math.sin(sphi);
    	c = Math.cos(sphi);
    	t = s / c;
    	eta = ebs * Math.pow(c, 2);
    	sn = a1 / Math.sqrt(1.0 - es * Math.pow(Math.sin(sphi), 2));
    	tmd = (((ap * sphi - bp * Math.sin(2 * sphi)) + cp * Math.sin(4 * sphi)) - dp * Math.sin(6 * sphi)) + ep * Math.sin(8 * sphi);
    	t1 = tmd * ok;
    	t2 = (sn * s * c * ok) / 2;
    	t3 = (sn * s * Math.pow(c, 3) * ok * ((5 - Math.pow(t, 2)) + 9 * eta + 4 * Math.pow(eta, 2))) / 24;
    	t4 = (sn * s * Math.pow(c, 5) * ok * (((((((61 - 58 * Math.pow(t, 2)) + Math.pow(t, 4) + 270 * eta) - 330 * Math.pow(t, 2) * eta) + 445 * Math.pow(eta, 2) + 324 * Math.pow(eta, 3)) - 680 * Math.pow(t, 2) * Math.pow(eta, 2)) + 88 * Math.pow(eta, 4)) - 600 * Math.pow(t, 2) * Math.pow(eta, 3) - 192 * Math.pow(t, 2) * Math.pow(eta, 4))) / 720;
    	t5 = (sn * s * Math.pow(c, 7) * ok * (((1385 - 3111 * Math.pow(t, 2)) + 543 * Math.pow(t, 4)) - Math.pow(t, 6))) / 40320;
    	xn1 = t1 + Math.pow(dlam, 2) * t2 + Math.pow(dlam, 4) * t3 + Math.pow(dlam, 6) * t4 + Math.pow(dlam, 8) * t5;
    	t6 = sn * c * ok;
    	t7 = (sn * Math.pow(c, 3) * ok * ((1.0 - Math.pow(t, 2)) + eta)) / 6;
    	t8 = (sn * Math.pow(c, 5) * ok * (((((5 - 18 * Math.pow(t, 2)) + Math.pow(t, 4) + 14 * eta) - 58 * Math.pow(t, 2) * eta) + 13 * Math.pow(eta, 2) + 4 * Math.pow(eta, 3)) - 64 * Math.pow(t, 2) * Math.pow(eta, 2) - 25 * Math.pow(t, 2) * Math.pow(eta, 3))) / 120;
    	t9 = (sn * Math.pow(c, 7) * ok * (((61 - 479 * Math.pow(t, 2)) + 179 * Math.pow(t, 4)) - Math.pow(t, 6))) / 5040;
    	double[] xy={	
		(xn1 - nfn) + x0, 
    	fe + dlam * t6 + Math.pow(dlam, 3) * t7 + Math.pow(dlam, 5) * t8 + Math.pow(dlam, 7) * t9
    	};
    	return xy;
      }
    
    /**
     * 
     * @param tmx
     * @param tmy
     * @return
     */
    private static double[] convertTM2KTM (double tmx, double tmy){
    	double[] KTM = TM2GP(tmy, tmx, tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(),tc.getBASE_TM_LON() + 0.0028902777777777776);
    	KTM = GP2TM(KTM[0], KTM[1], tc.getM_AB(), tc.getM_FB(), tc.getM_x1(), tc.getM_y1(), tc.getM_OKGTM(), tc.getBASE_KTM_LAT(), tc.getBASE_KTM_LON());
    	KTM = changeXY(KTM);
    	KTM[0] = Math.round(KTM[0]);
    	KTM[1] = Math.round(KTM[1]);
    	return KTM;
    }
    
    /**
     * 
     * @param tmx
     * @param tmy
     * @return
     */
    private static double[] convertTM2BesselKTM (double tmx, double tmy){
    	double[] KTM = TM2GP(tmy, tmx, tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(),tc.getBASE_TM_LON() + 0.0028902777777777776);
    	KTM = GP2TM(KTM[0], KTM[1], tc.getM_AB(), tc.getM_FB(), tc.getM_x1(), tc.getM_y1(), tc.getM_OKGTM(), tc.getBASE_KTM_LAT(), tc.getBASE_KTM_LON());
    	KTM = changeXY(KTM);
    	KTM[0] = Math.round(KTM[0]/ 36000);
    	KTM[1] = Math.round(KTM[1]/ 36000);
    	return KTM;
    }
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @param hw
     * @param a1
     * @param f
     * @return
     */
    private static double[] WGP2GP (double latitude, double longitude, double hw, double a1, double f){
		double[] WCTR = WGP2WCTR(latitude, longitude, hw);
		WCTR = TransMolod(WCTR[0], WCTR[1], WCTR[2]);
		double[] GP = CTR2GP(WCTR[0], WCTR[1], WCTR[2], a1, f);
		GP[0] = GP[0] + 0.0;
		GP[1] = GP[1] + 0.0;
		return GP;
	  }
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @param h
     * @return
     */
    private static double[] WGP2WCTR (double latitude, double longitude, double h) {
		return GP2CTR(latitude, longitude, h, tc.getM_AW(), tc.getM_FW());
	  }
    
    /**
     * 
     * @param xw
     * @param yw
     * @param zw
     * @return
     */
    private static double[] TransMolod (double xw, double yw, double zw) {
    	double[] xyz = {
			xw + (1.0 + tc.getM_ds()) * (tc.getM_kappa() * yw - tc.getM_omega() * zw) + tc.getM_dx(),
			yw + (1.0 + tc.getM_ds()) * (-tc.getM_kappa() * xw + tc.getM_omega() * zw) + tc.getM_dy(),
			zw + (1.0 + tc.getM_ds()) * (tc.getM_phi() * xw - tc.getM_omega() * yw) + tc.getM_dz()
		};
		return xyz;
	}
    
    /**
     * 
     * @param longitude
     * @param latitude
     * @return
     */
    private static double[] convertWGS2TM (double longitude, double latitude){
    	
    	setParameter(tc.getM_TX(),tc.getM_TY(), tc.getM_TZ(), tc.getM_omega(), tc.getM_phi(), tc.getM_kappa(), tc.getM_ds(), 1);
    	double[] TM = WGP2GP(latitude, longitude, 0.0, tc.getM_AB(), tc.getM_FB());
    	TM = GP2TM(TM[0], TM[1], tc.getM_AB(), tc.getM_FB(), tc.getM_x0(), tc.getM_y0(), 1.0, tc.getBASE_TM_LAT(),tc.getBASE_TM_LON()+ 0.0028902777777777776);
    	TM = changeXY(TM);
    	TM[0] = Math.round(TM[0]);
    	TM[1] = Math.round(TM[1]);
    	return TM;
    }    
    
    /**
     * 
     * @param x
     * @param y
     * @param z
     * @param a1
     * @param f1
     * @return
     */
    private static double[] CTR2GP (double x, double y, double z, double a1, double f1){
    	double degrad = 0.0;
    	double sphiold = 0.0;
    	double sphinew = 0.0;
    	double slam = 0.0;
    	double recf = 0.0;
    	double b = 0.0;
    	double es = 0.0;
    	double n = 0.0;
    	double p = 0.0;
    	double t1 = 0.0;
    	double f = 0.0;
    	double h = 0.0;
    	f = f1;
		if(f > 1.0)
			f = 1.0 / f;
		degrad = Math.atan(1.0) / 45;
		recf = 1.0 / f;
		b = (a1 * (recf - 1.0)) / recf;
		es = (Math.pow(a1, 2) - Math.pow(b, 2)) / Math.pow(a1, 2);
		slam = Math.atan(y / x);
		p = Math.sqrt(x * x + y * y);
		n = a1;
		int i = 0;
		do{
			i++;
			t1 = Math.pow((Math.pow(b, 2) / Math.pow(a1, 2)) * n + h, 2) - Math.pow(z, 2);
			t1 = z / Math.sqrt(t1);
			sphinew = Math.atan(t1);
			if(Math.abs(sphinew - sphiold) < 1.0000000000000001E-018)
				break;
			n = a1 / Math.sqrt(1.0 - es * Math.pow(Math.sin(sphinew), 2));
			h = p / Math.cos(sphinew) - n;
			sphiold = sphinew;
		} while(i <= 30);
		double[] xy={sphinew / degrad, slam / degrad};
		if(x < 0.0)
			xy[1] = 180 + xy[1];
		if(xy[1] < 0.0)
			xy[1] = 360 + xy[1];
		return xy;
	}
    
    /**
     * 
     * @param latitude
     * @param longitude
     * @param h
     * @param a1
     * @param f1
     * @return
     */
    private static double[] GP2CTR (double latitude, double longitude, double h, double a1, double f1){
    	double degrad = 0.0;
    	double sphi = 0.0;
    	double slam = 0.0;
    	double recf = 0.0;
    	double b = 0.0;
    	double es = 0.0;
    	double n = 0.0;
    	double f = 0.0;
    	f = f1;
    	if(f > 1.0)
    		f = 1.0 / f;
    	degrad = Math.atan(1.0) / 45;
    	sphi = latitude * degrad;
    	slam = longitude * degrad;
    	recf = 1.0 / f;
    	b = (a1 * (recf - 1.0)) / recf;
    	es = (Math.pow(a1, 2) - Math.pow(b, 2)) / Math.pow(a1, 2);
    	n = a1 / Math.sqrt(1.0 - es * Math.pow(Math.sin(sphi), 2));
    	double xyz[] = {
    		(n + h) * Math.cos(sphi) * Math.cos(slam), 
    		(n + h) * Math.cos(sphi) * Math.sin(slam), 
    		((Math.pow(b, 2) / Math.pow(a1, 2)) * n + h) * Math.sin(sphi)
    	};
    	return xyz;
    }
    
    private static CoordsUtil conv = null;
    private static double[] defaultPoint = {
        0.0, 0.0
    };
    private static double[] points = null;

}
