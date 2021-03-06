import { Component, OnInit } from '@angular/core';
import {PostService} from "./post.service";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor(private postService: PostService) {}

  ngOnInit() {
    this.getPosts();
  }

  getPosts() {
    this.postService.getPosts()
      .subscribe(
        (response) => console.log(response),
        (error) => console.log(error)
      )
  }

}
