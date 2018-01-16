package by.epam.selection.service;

import by.epam.selection.entity.Faculty;
import by.epam.selection.service.exception.ServiceException;

import java.util.List;

/**
 * Created by lex on 12/17/2017.
 */
public interface FacultyService {
    List<Faculty> getAll() throws ServiceException;

    Faculty get(long facultyId) throws ServiceException;
}
