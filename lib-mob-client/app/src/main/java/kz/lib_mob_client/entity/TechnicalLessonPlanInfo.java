package kz.lib_mob_client.entity;


public class TechnicalLessonPlanInfo {
    private int id;
    private String lessonType;
    private String techLessonTheme;
    private String position;
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getTechLessonTheme() {
        return techLessonTheme;
    }

    public void setTechLessonTheme(String techLessonTheme) {
        this.techLessonTheme = techLessonTheme;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
