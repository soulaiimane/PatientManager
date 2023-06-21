package ma.enset.Patientsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.Patientsmvc.entities.Patient;
import ma.enset.Patientsmvc.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping(path = "/index")
    public String PagePatients(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "keyword", defaultValue = "") String keyword,
                               @RequestParam(name = "size", defaultValue = "6") int size) {
        Page<Patient> pagePatient = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

        @GetMapping("/delete")
        public String delete (Long id,String keyword,int page){
            patientRepository.deleteById(id);
            return "redirect:/index?page="+page+"&keyword"+keyword;
        }
        @GetMapping("/")
        public String home () {
            return "redirect:/index";
        }
    }
