package com.juliasoft.rest;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * CalculatorService with Cache
 */
public class CachedCalculatorService extends CalculatorService {
    @Override
    public double add(double... values) {
        return addCache.computeIfAbsent(StringUtils.join(values, "/"), (k) -> super.add(values));
    }

    @Override
    public double subtract(double... values) {
        return subCache.computeIfAbsent(StringUtils.join(values, "/"), (k) -> super.subtract(values));
    }

    @Override
    public double multiply(double... values) {
        return mulCache.computeIfAbsent(StringUtils.join(values, "/"), (k) -> super.multiply(values));
    }

    @Override
    public double divide(double... values) {
        return divCache.computeIfAbsent(StringUtils.join(values, "/"), (k) -> super.divide(values));
    }

    private static final ConcurrentHashMap<String, Double> addCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Double> subCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Double> mulCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Double> divCache = new ConcurrentHashMap<>();
}
