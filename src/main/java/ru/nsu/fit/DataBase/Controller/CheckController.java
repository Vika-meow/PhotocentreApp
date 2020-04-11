package ru.nsu.fit.DataBase.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.nsu.fit.DataBase.Repos.CheckRepo;

@Controller
public class CheckController {
    @Autowired
    CheckRepo checkRepo;


}
