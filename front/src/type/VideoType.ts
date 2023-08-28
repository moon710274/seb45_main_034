export type Movie = {
    moviesID: number;
    title: string;
    genre: string;
  };
  
  export type MoviesResponse = {
    movies: Movie[];
  };