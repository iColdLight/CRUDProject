package net.proselyte.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.proselyte.crudapp.model.Developer;
import net.proselyte.crudapp.model.Skill;
import net.proselyte.crudapp.repository.DeveloperRepository;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonDeveloperRepositoryImpl implements DeveloperRepository {
    private final Gson gson = new Gson();
    private final String DEVELOPERS_FILE_PATH = "src/main/resources/developers.json";

    private List<Developer> getAllDevelopers() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(DEVELOPERS_FILE_PATH));
            List<Developer> developers = gson.fromJson(bufferedReader, new TypeToken<ArrayList<Skill>>() {
            }.getType());
            bufferedReader.close();
            return developers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeDevelopersToFile(List<Developer> developers){
        try {
            gson.toJson(developers, new FileWriter(DEVELOPERS_FILE_PATH));
        } catch (IOException e) {
            System.out.println("Exception + " + e);
        }
    }

    private Long generateId (List<Developer> developers){
        Developer maxDeveloper = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null);
        return Objects.nonNull(maxDeveloper) ? maxDeveloper.getId() + 1 : 1L;
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> currentDevelopers = getAllDevelopers();
        Long id = generateId(currentDevelopers);
        developer.setId(id);
        currentDevelopers.add(developer);
        writeDevelopersToFile(currentDevelopers);
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        List<Developer> currentDeveloper = getAllDevelopers();
        currentDeveloper.forEach(s->{
            if (s.getId().equals(developer.getId())) {
                s.setFirstName(developer.getFirstName());
                s.setLastName(developer.getLastName());
            }
        });
        writeDevelopersToFile(currentDeveloper);
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return getAllDevelopers();
    }

    @Override
    public Developer getByID(Long id) {
        return getAllDevelopers().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        List<Developer> currentSkill = getAllDevelopers();
        currentSkill.removeIf(s -> s.getId().equals(id));
        writeDevelopersToFile(currentSkill);
    }
}
