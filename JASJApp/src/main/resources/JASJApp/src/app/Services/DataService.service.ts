import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";

@Injectable()
export class DataService {

    constructor (private http: HttpClient) {
    }

//  API CALLS

    public getUsers() : Promise<any> {
        return firstValueFrom(this.http.get<String>('http://localhost:8080/JAS/v1/users', { withCredentials: false }));
    }
}