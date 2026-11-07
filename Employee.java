package payrollsystem;

import java.time.LocalDate;
import java.util.Locale;

public abstract class Employee implements Taxable, BonusCalculable, Comparable<Employee> {

    private final String id;
    private final String name;
    private final LocalDate joinDate;
    protected double baseSalary;

    public Employee(String id, String name, double baseSalary, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
    }

    public abstract double calculateNetSalary();

    @Override
    public abstract double calculateBonus(double performanceScore);

    // الضريبة = 15% من صافي الراتب
    @Override
    public double calculateTax() {
        return calculateNetSalary() * TAX_RATE;
    }

    // تنازلي: الأعلى دخلاً أولاً
    @Override
    public int compareTo(Employee other) {
        return Double.compare(other.calculateNetSalary(), this.calculateNetSalary());
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getBaseSalary() { return baseSalary; }
    public LocalDate getJoinDate() { return joinDate; }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "[%s] %-14s | joined: %s | net: %,10.2f",
                id, name, joinDate, calculateNetSalary());
    }
}
