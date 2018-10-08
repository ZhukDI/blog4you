import { Injectable } from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Post} from "./post";

@Injectable()
export class PostService {
  readonly ROOT_URL = 'https://localhost:8087';

  constructor(private http: HttpClient) {}

  storePost(posts: Post[]) {
    this.http.post(this.ROOT_URL + '/posts', posts);
  }

  getPosts() {
    return this.http.get<Post[]>(this.ROOT_URL + '/posts');
  }
}
