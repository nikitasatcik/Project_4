package nikita.epam.project_4.model.dao;

import nikita.epam.project_4.model.entity.Tour;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class TourDAO {
	public abstract Iterable<Tour> findTours();
	public abstract List<Tour> tourList();

	protected void closeStatement(Statement st){
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println(" cannot closeStatement statement");
			}
		}
	}
}
