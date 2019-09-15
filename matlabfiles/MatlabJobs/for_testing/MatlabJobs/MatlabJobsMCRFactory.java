/*
 * MATLAB Compiler: 6.5 (R2017b)
 * Date: Mon Jul 30 16:47:22 2018
 * Arguments: 
 * "-B""macro_default""-W""java:MatlabJobs,MatlabCompute""-T""link:lib""-d""D:\\EclP\\matlabfiles\\MatlabJobs\\for_testing""class{MatlabCompute:D:\\EclP\\matlabfiles\\Entropy_OW_G2.m,D:\\EclP\\matlabfiles\\Entropy_OW_G3.m}"
 */

package MatlabJobs;

import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.internal.*;

/**
 * <i>INTERNAL USE ONLY</i>
 */
public class MatlabJobsMCRFactory
{
   
    
    /** Component's uuid */
    private static final String sComponentId = "MatlabJobs_6F81131E847A1457B049AB2391AD90F1";
    
    /** Component name */
    private static final String sComponentName = "MatlabJobs";
    
   
    /** Pointer to default component options */
    private static final MWComponentOptions sDefaultComponentOptions = 
        new MWComponentOptions(
            MWCtfExtractLocation.EXTRACT_TO_CACHE, 
            new MWCtfClassLoaderSource(MatlabJobsMCRFactory.class)
        );
    
    
    private MatlabJobsMCRFactory()
    {
        // Never called.
    }
    
    public static MWMCR newInstance(MWComponentOptions componentOptions) throws MWException
    {
        if (null == componentOptions.getCtfSource()) {
            componentOptions = new MWComponentOptions(componentOptions);
            componentOptions.setCtfSource(sDefaultComponentOptions.getCtfSource());
        }
        return MWMCR.newInstance(
            componentOptions, 
            MatlabJobsMCRFactory.class, 
            sComponentName, 
            sComponentId,
            new int[]{9,3,0}
        );
    }
    
    public static MWMCR newInstance() throws MWException
    {
        return newInstance(sDefaultComponentOptions);
    }
}
