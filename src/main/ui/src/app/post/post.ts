import DateTimeFormat = Intl.DateTimeFormat;

export interface Post {
  id: number;
  userId: number;
  title: string;
  dateCreated: DateTimeFormat;
  dateUpdated: DateTimeFormat;

}
