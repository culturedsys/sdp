package proxy.virtualproxy

case class Employee(
  var employeeName: String,
  var employeeSalary: Double,
  var employeeDesignation: String
) {
  println("Employee created")
  override def toString(): String = s"Employee name: $employeeName, Employee Designation = $employeeDesignation, Employee Salary = $employeeSalary"
}
