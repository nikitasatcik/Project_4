package nikita.epam.project_4.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import nikita.epam.project_4.model.entity.Tour;

public class SQLTourDAO extends TourDAO {

	private final static String SQL_SELECT_FROM_TOURS = "SELECT * FROM tours";

	@Override
	public Iterable<Tour> findTours() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		ArrayList<Tour> tours = null;
		try {
			// Get Connection and create Statement.
			connection = ConnectionPool.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_FROM_TOURS);

			while (resultSet.next()) {
				if (tours == null) {
					tours = new ArrayList<>();
				}
				Tour dbTour = new Tour();
				dbTour.setTour_id(resultSet.getInt("tour_id"));
				dbTour.setCountry_id(resultSet.getInt("country_id"));
				dbTour.setTour_name(resultSet.getString("tour_name"));
				dbTour.setTour_description(resultSet.getString("tour_description"));
				tours.add(dbTour);
			}
			return tours;
		} catch (SQLException e) {
			System.out.println("MySQLRouteDAO.findTours() error");
		} finally {
			super.closeStatement(statement);
			ConnectionPool.closeConnection(connection);
			try {
				assert resultSet != null;
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	@Override
	public List<Tour> tourList() {
		List<Tour> tours = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionPool.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_TOURS);
			while (resultSet.next()) {
				Tour tour = new Tour();
				tour.setTour_id(resultSet.getInt("tour_id"));
				tour.setCountry_id(resultSet.getInt("country_id"));
				tour.setTour_name(resultSet.getString("tour_name"));
				tour.setTour_description(resultSet.getString("tour_description"));
				tours.add(tour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeStatement(statement);
			ConnectionPool.closeConnection(connection);
		}
		return tours;
	}
}
