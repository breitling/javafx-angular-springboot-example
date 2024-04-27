import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { DataService } from './Services/DataService.service';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    providers: [DataService, HttpClient]
})
export class AppComponent implements OnInit {
    title = 'JASApp';
    
    public users: string;

    //@ts-ignore
    private getJavaConnector: Function = window['getJavaConnector'];
    //@ts-ignore
    private getJsConnector: Function = window['getJsConnector'];

    constructor(private dataService : DataService) {
        console.log("JASApp");
        this.users = '';
    }

    ngOnInit(): void {
    }

    public exit() {
    //  console.log("Exitting with " + this.getJavaConnector());
        this.getJavaConnector().exit(0);
    }

    public getUsers() {
        this.dataService.getUsers().then((d : string) => {
            console.log(d);
            this.users = d;
        });
    }

    public getConnectorUsers() {
        this.users = this.getJavaConnector().getUsers();
    }
}
