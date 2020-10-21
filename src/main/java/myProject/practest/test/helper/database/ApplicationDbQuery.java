package myProject.practest.test.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDbQuery {

	public List<NameValueDetails> getNameValue() throws SQLException {
		List<NameValueDetails> details = new ArrayList<NameValueDetails>();
		String dbQuery = "SELECT Value,Name FROM APPCONFIG where settingstype = 'WILDCARD' order by Name";
		ResultSet results = DataBaseHelper.getResultSet(dbQuery);
		while (results.next()) {
			NameValueDetails namevalue = new NameValueDetails();
			namevalue.setName(results.getString("Name"));
			namevalue.setValue(results.getString("Value"));
			details.add(namevalue);
		}
		return details;

	}
	public static void main(String arg[]) throws SQLException
	{
		ApplicationDbQuery aq = new ApplicationDbQuery();
		List<NameValueDetails> details = aq.getNameValue();
		for(NameValueDetails detail : details)
		{
			System.out.println("Name is:"+detail.getName()+"----"+"Value is:"+detail.getValue());
		}
				
	}
}


