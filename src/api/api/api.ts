export * from './employeeEntityController.service';
import { EmployeeEntityControllerService } from './employeeEntityController.service';
export * from './employeePropertyReferenceController.service';
import { EmployeePropertyReferenceControllerService } from './employeePropertyReferenceController.service';
export * from './profileController.service';
import { ProfileControllerService } from './profileController.service';
export const APIS = [EmployeeEntityControllerService, EmployeePropertyReferenceControllerService, ProfileControllerService];
