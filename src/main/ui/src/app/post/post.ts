import DateTimeFormat = Intl.DateTimeFormat;

export interface Post {
  id: number;
  userId: number;
  title: string;
  body: string;
  dateCreated: DateTimeFormat;
  dateUpdated: DateTimeFormat;

}
