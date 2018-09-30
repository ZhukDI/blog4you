import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Post } from "./post";
import { Observable } from "rxjs";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  readonly ROOT_URL = 'https://localhost:8087';

  posts: Observable<Post[]>;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  getPosts() {
    this.posts = this.http.get<Post[]>(this.ROOT_URL + '/posts');
  }

}
