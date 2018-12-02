package login;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class EXecution_Method {
	public Object runReflectionMethod(String strClassName, String strMethodName,Object[] inputArgs) {

		Object result="";
		
		Class<?> params[] = new Class[inputArgs.length];

		for (int i = 0; i < inputArgs.length; i++) {
			if (inputArgs[i] instanceof String) {
				params[i] = String.class;
			}
		}
		try {
			Class<?> cls = Class.forName(strClassName);
			Object _instance = cls.newInstance();
			Method myMethod = cls.getDeclaredMethod(strMethodName, params);
			 result=myMethod.invoke(_instance, inputArgs);
			//Field ff[] = cls.getFields();
			//System.out.println(ff);
					
			
		} catch (ClassNotFoundException e) {
			System.err.format(strClassName + ":- Class not found%n");
		} catch (IllegalArgumentException e) {
			System.err.format("Method invoked with wrong number of arguments%n");
		} catch (NoSuchMethodException e) {
			System.err.format("In Class " + strClassName + "::" + strMethodName+ ":- method does not exists%n");
		} catch (InvocationTargetException e) {
			if(e.getCause().getClass().equals(AssertionError.class))
			System.err.format("Exception thrown by an invoked method%n");
		} catch (IllegalAccessException e) {
			System.err.format("Can not access a member of class with modifiers private%n");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.format("Object cannot be instantiated for the specified class using the newInstance method%n");
		}	
		catch (SecurityException e) {
			System.err.format("Object cannot be instantiated for the specified class using the newInstance method%n");
		}
		
		catch (Exception e) {
			System.err.format("Object cannot be instantiated for the specified class using the newInstance method%n");
		}
		
		return result;
	}

	
	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		
		try{
			EXecution_Method exeKey = new EXecution_Method();
		Excel_data excelSheet = new Excel_data();
		String sPath = "‪‪C:\\Test data\\abc.txt";//dataop.xlsx
		excelSheet.openSheet(sPath,"Form1");
		
		for (int row = 1; row <= excelSheet.getRowCount(); row++) {
			List<Object> myParamList = new ArrayList<Object>();
			String methodName = excelSheet.getValueFromCella(1, row);
			for (int col = 2; col < excelSheet.getColumnCount(); col++) 
			{
				if (!excelSheet.getValueFromCella(col, row).isEmpty()& !excelSheet.getValueFromCella(col, row).equals("null")) {
					myParamList.add(excelSheet.getValueFromCella(col, row));
				}
		
			}

			Object[] paramListObject = new String[myParamList.size()];
			paramListObject = myParamList.toArray(paramListObject);

			Object m1=exeKey.runReflectionMethod("login.Account_Method",methodName, paramListObject);
			String result= String.valueOf(m1);
			String pathout="C:\\Users\\om.yadav.HUMANITICSIT\\Desktop\\Test_Report\\Foxministry_Report_App.xls";
			//if(myMethod.getName()==true)
			String TestCase=excelSheet.getValueFromCella(1, row);
			if(TestCase.equalsIgnoreCase(methodName)){
			String testcaseid = excelSheet.getValueFromCella(0, row);
			String testdata = excelSheet.getValueFromCella(4, row);
			excelSheet.writeSheet(pathout,row, testcaseid,methodName,testdata,result,excelSheet.getRowCount());
			//excelSheet.writeSheet(pathout,row,result,excelSheet.getRowCount());
		}
	
		}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		}
		
}
