package com.nhson.traditionlibraryservice.spec;

import com.nhson.traditionlibraryservice.model.EventType;
import com.nhson.traditionlibraryservice.model.Region;
import com.nhson.traditionlibraryservice.model.TraditionalEvent;
import org.springframework.data.jpa.domain.Specification;

public class TraditionalEventSpecifications {

    public static Specification<TraditionalEvent> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.trim().isEmpty()) {
                return null;
            }
            String lowerKeyword = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), lowerKeyword),
                    cb.like(cb.lower(root.get("alias")), lowerKeyword),
                    cb.like(cb.lower(root.get("description")), lowerKeyword)
            );
        };
    }

    public static <T> Specification<T> and(Specification<T> base, Specification<T> next) {
        if (base == null) return next;
        if (next == null) return base;
        return base.and(next);
    }

    public static Specification<TraditionalEvent> hasLunarDay(Integer day) {
        return (root, query, cb) -> day == null ? null :
                cb.equal(root.get("lunarDay"), day);
    }

    public static Specification<TraditionalEvent> hasLunarMonth(Integer month) {
        return (root, query, cb) -> month == null ? null :
                cb.equal(root.get("lunarMonth"), month);
    }

    public static Specification<TraditionalEvent> hasType(EventType type) {
        return (root, query, cb) -> type == null ? null :
                cb.equal(root.get("type"), type);
    }

    public static Specification<TraditionalEvent> hasRegion(Region region) {
        return (root, query, cb) -> region == null ? null :
                cb.isMember(region, root.get("regions"));
    }

    public static Specification<TraditionalEvent> hasRepeatAnnually(Boolean repeatAnnually) {
        return (root, query, cb) -> repeatAnnually == null ? null :
                cb.equal(root.get("repeatAnnually"), repeatAnnually);
    }

    public static Specification<TraditionalEvent> hasImportanceLevel(Integer importanceLevel) {
        return (root, query, cb) -> importanceLevel == null ? null :
                cb.equal(root.get("importanceLevel"), importanceLevel);
    }

    public static Specification<TraditionalEvent> hasTag(String tag) {
        return (root, query, cb) -> {
            if (tag == null || tag.trim().isEmpty()) {
                return null;
            }
            return cb.isMember(tag.toLowerCase(), root.get("tags"));
        };
    }

    public static Specification<TraditionalEvent> importanceLevelGreaterThan(Integer level) {
        return (root, query, cb) -> level == null ? null :
                cb.greaterThan(root.get("importanceLevel"), level);
    }

    public static Specification<TraditionalEvent> importanceLevelLessThan(Integer level) {
        return (root, query, cb) -> level == null ? null :
                cb.lessThan(root.get("importanceLevel"), level);
    }
}