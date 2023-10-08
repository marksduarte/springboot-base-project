package dev.marksduarte.springbootbase.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchFilterParam {

    private String key;
    private Object value;
    private SearchFilterCondition condition;

    public boolean isOperation(SearchFilterCondition op) {
        return this.condition.equals(op);
    }
}
