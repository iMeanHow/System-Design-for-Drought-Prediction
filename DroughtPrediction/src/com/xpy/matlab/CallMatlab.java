package com.xpy.matlab;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import MatlabJobs.*;
public class CallMatlab {

	public void excute() throws Exception
	{
		MatlabCompute s=new MatlabCompute();

	    MWNumericArray ax = null; // 用于保存矩阵
	    double x[] = { 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1 };
	    ax = new MWNumericArray(x, MWClassID.DOUBLE);
		try {
			
			s.Entropy_OW_G2(ax);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s.Entropy_OW_G3(ax);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
