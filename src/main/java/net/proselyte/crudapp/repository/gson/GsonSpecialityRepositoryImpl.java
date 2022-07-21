package net.proselyte.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.proselyte.crudapp.model.Skill;
import net.proselyte.crudapp.model.Speciality;
import net.proselyte.crudapp.repository.SpecialityRepository;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSpecialityRepositoryImpl implements SpecialityRepository {
    private final Gson gson = new Gson();
    private final String SPECIALITY_FILE_PATH = "src/main/resources/speciality.json";

    private List<Speciality> getAllSpecialities() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(SPECIALITY_FILE_PATH));
            List<Speciality> specialities = gson.fromJson(bufferedReader, new TypeToken<ArrayList<Skill>>() {
            }.getType());
            bufferedReader.close();
            return specialities;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeSpecialitiesToFile(List<Speciality> specialities) {
        try {
            gson.toJson(specialities, new FileWriter(SPECIALITY_FILE_PATH));
        } catch (IOException e) {
            System.out.println("Exception + " + e);
        }
    }

    private Long generateId(List<Speciality> specialities) {
        Speciality maxSpeciality = specialities.stream().max(Comparator.comparing(Speciality::getId)).orElse(null);
        return Objects.nonNull(maxSpeciality) ? maxSpeciality.getId() + 1 : 1L;
    }

    @Override
    public List<Speciality> getAll() {
        return getAllSpecialities();
    }

    @Override
    public Speciality getByID(Long id) {
        return getAllSpecialities().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        List<Speciality> currentSpecialities = getAllSpecialities();
        Long id = generateId(currentSpecialities);
        speciality.setId(id);
        currentSpecialities.add(speciality);
        writeSpecialitiesToFile(currentSpecialities);
        return speciality;
    }

    @Override
    public Speciality update(Speciality speciality) {
        List<Speciality> currentSpeciality = getAllSpecialities();
        currentSpeciality.forEach(s->{
            if (s.getId().equals(speciality.getId())) {
                s.setSpecialityName(speciality.getSpecialityName());
            }
        });
        writeSpecialitiesToFile(currentSpeciality);
        return speciality;
    }

    @Override
    public void deleteById(Long id) {
        List<Speciality> currentSpeciality = getAllSpecialities();
        currentSpeciality.removeIf(s -> s.getId().equals(id));
        writeSpecialitiesToFile(currentSpeciality);
    }
}
