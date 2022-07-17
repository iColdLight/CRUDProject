package net.proselyte.crudapp.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.proselyte.crudapp.model.Skill;
import net.proselyte.crudapp.repository.SkillRepository;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final Gson gson = new Gson();
    private final String SKILL_FILE_PATH = "src/main/resources/skills.json";

    private List<Skill> getAllSkills() {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(SKILL_FILE_PATH));
            List<Skill> skills = gson.fromJson(bufferedReader, new TypeToken<ArrayList<Skill>>() {
            }.getType());
            bufferedReader.close();
            return skills;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void writeSkillsToFile(List<Skill> skills) {
        try {
            gson.toJson(skills, new FileWriter(SKILL_FILE_PATH));
        } catch (IOException e) {
            System.out.println("Exception + " + e);
        }
    }

    private Long generateId(List<Skill> skills) {
        Skill maxSkill = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        return Objects.nonNull(maxSkill) ? maxSkill.getId() + 1 : 1L;
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> currentSkills = getAllSkills();
        Long id = generateId(currentSkills);
        skill.setId(id);
        currentSkills.add(skill);
        writeSkillsToFile(currentSkills);
        return skill;
    }

    @Override
    public Skill update (Skill skill){
        return null;
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkills();
    }

    @Override
    public Skill getByID(Long id) {
        return getAllSkills().stream().filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        List<Skill> currentSkill = getAllSkills();
        currentSkill.removeIf(s -> s.getId().equals(id));
        writeSkillsToFile(currentSkill);
    }

}
