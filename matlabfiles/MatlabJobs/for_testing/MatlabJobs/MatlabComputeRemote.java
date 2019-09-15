/*
 * MATLAB Compiler: 6.5 (R2017b)
 * Date: Mon Jul 30 16:47:22 2018
 * Arguments: 
 * "-B""macro_default""-W""java:MatlabJobs,MatlabCompute""-T""link:lib""-d""D:\\EclP\\matlabfiles\\MatlabJobs\\for_testing""class{MatlabCompute:D:\\EclP\\matlabfiles\\Entropy_OW_G2.m,D:\\EclP\\matlabfiles\\Entropy_OW_G3.m}"
 */

package MatlabJobs;

import com.mathworks.toolbox.javabuilder.pooling.Poolable;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The <code>MatlabComputeRemote</code> class provides a Java RMI-compliant interface to 
 * MATLAB functions. The interface is compiled from the following files:
 * <pre>
 *  D:\\EclP\\matlabfiles\\Entropy_OW_G2.m
 *  D:\\EclP\\matlabfiles\\Entropy_OW_G3.m
 * </pre>
 * The {@link #dispose} method <b>must</b> be called on a 
 * <code>MatlabComputeRemote</code> instance when it is no longer needed to ensure that 
 * native resources allocated by this class are properly freed, and the server-side proxy 
 * is unexported.  (Failure to call dispose may result in server-side threads not being 
 * properly shut down, which often appears as a hang.)  
 *
 * This interface is designed to be used together with 
 * <code>com.mathworks.toolbox.javabuilder.remoting.RemoteProxy</code> to automatically 
 * generate RMI server proxy objects for instances of MatlabJobs.MatlabCompute.
 */
public interface MatlabComputeRemote extends Poolable
{
    /**
     * Provides the standard interface for calling the <code>Entropy_OW_G2</code> MATLAB 
     * function with 1 input argument.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * %%B(i,j) = (b-R(i,j))/(b-a)  %低优指标处理，负向指标数值越低越好
     * </pre>
     *
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] Entropy_OW_G2(Object... rhs) throws RemoteException;
    /**
     * Provides the standard interface for calling the <code>Entropy_OW_G3</code> MATLAB 
     * function with 1 input argument.  
     *
     * Input arguments to standard interface methods may be passed as sub-classes of 
     * <code>com.mathworks.toolbox.javabuilder.MWArray</code>, or as arrays of any 
     * supported Java type (i.e. scalars and multidimensional arrays of any numeric, 
     * boolean, or character type, or String). Arguments passed as Java types are 
     * converted to MATLAB arrays according to default conversion rules.
     *
     * All inputs to this method must implement either Serializable (pass-by-value) or 
     * Remote (pass-by-reference) as per the RMI specification.
     *
     * Documentation as provided by the author of the MATLAB function:
     * <pre>
     * % function B1weights = EntropyWeight(RB1)   % 
     * 熵权法求指标权重,R为输入矩阵,返回权重向量weights
     * </pre>
     *
     * @param rhs The inputs to the MATLAB function.
     *
     * @return Array of length nargout containing the function outputs. Outputs are 
     * returned as sub-classes of <code>com.mathworks.toolbox.javabuilder.MWArray</code>. 
     * Each output array should be freed by calling its <code>dispose()</code> method.
     *
     * @throws java.rmi.RemoteException An error has occurred during the function call or 
     * in communication with the server.
     */
    public Object[] Entropy_OW_G3(Object... rhs) throws RemoteException;
  
    /** 
     * Frees native resources associated with the remote server object 
     * @throws java.rmi.RemoteException An error has occurred during the function call or in communication with the server.
     */
    void dispose() throws RemoteException;
}
