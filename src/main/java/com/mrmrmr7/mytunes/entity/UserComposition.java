package com.mrmrmr7.mytunes.entity;

        import com.mrmrmr7.mytunes.dao.Identified;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Objects;

public class UserComposition implements Identified<Integer> {
    private Integer id;
    private List<Integer> cortageIdList = new ArrayList<>();
    private List<Integer> compositionIdList = new ArrayList<>();

    public UserComposition(Integer userId, List<Integer> cortageIdList, List<Integer> compositionIdList) {
        this.id = userId;
        this.cortageIdList = cortageIdList;
        this.compositionIdList = compositionIdList;
    }

    public UserComposition(Integer cortageId, Integer userId, Integer compositionId) {
        this(userId, new ArrayList<>(Arrays.asList(cortageId)), new ArrayList<>(Arrays.asList(compositionId)));
    }

    public UserComposition(Integer userId, Integer compositionId) {
        this(0, userId, compositionId);
    }

    public UserComposition() {
    }

    public void setId(Integer userId) {
        this.id = userId;
    }

    public List<Integer> getCortageIdList() {
        return cortageIdList;
    }

    public void setCortageIdList(List<Integer> cortageIdList) {
        this.cortageIdList = cortageIdList;
    }

    public List<Integer> getCompositionIdList() {
        return compositionIdList;
    }
    public Integer getCompositionId(int id) {
        return compositionIdList.get(id);
    }

    public Integer getCortageId(int id) {
        return cortageIdList.get(id);
    }


    public void setCompositionIdList(List<Integer> compositionIdList) {
        this.compositionIdList = compositionIdList;
    }

    @Override
    public String toString() {
        return "UserComposition{" +
                "id=" + id +
                ", cortageIdList=" + cortageIdList +
                ", compositionIdList=" + compositionIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComposition userComposition = (UserComposition) o;
        return Objects.equals(id, userComposition.id) &&
                Objects.equals(cortageIdList, userComposition.cortageIdList) &&
                Objects.equals(compositionIdList, userComposition.compositionIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cortageIdList, compositionIdList);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void addCompositionId(Integer id, Integer compositionId) {
        this.cortageIdList.add(id);
        this.compositionIdList.add(compositionId);
    }
}
