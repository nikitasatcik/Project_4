package nikita.epam.project_4.model.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nikita.epam.project_4.model.dao.DAOFactory;
import nikita.epam.project_4.model.dao.TourDAO;
import nikita.epam.project_4.model.entity.Tour;

public class GetAllToursCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Iterable<Tour> tours = daoFactory.getTourDAO().findTours();
        request.setAttribute("tour", tours);
    }
}