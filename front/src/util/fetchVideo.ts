import express, { Request, Response } from 'express';
import { Movie } from '../type/VideoType';
const app = express();
const port = 3001;

app.use(express.json());

const movies: Movie[] = [
  { moviesID: 1, title: '영화1', genre: 'SF' },
  { moviesID: 2, title: '영화2', genre: '무료' },
];

app.get('/api/movies', (req: Request, res: Response) => {
  res.json({ movies });
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
