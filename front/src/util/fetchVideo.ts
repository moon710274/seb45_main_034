import express, { Request, Response } from 'express';
import { HistoryRecord, Movie } from '../type/VideoType';
const app = express();
const port = 3001;

app.use(express.json());

const historyDatabase: HistoryRecord[] = [];

//영화 목록 조회 api
const movies: Movie[] = [
  { moviesID: 1, title: '영화1', genre: 'SF' },
  { moviesID: 2, title: '영화2', genre: '무료' },
];

app.get('/api/movies', (req: Request, res: Response) => {
  res.json({ movies });
});

app.listen(port, () => {
  console.log(`아마 서버 작동중일듯합니다 ${port}`);
});

//시청기록 저장 api
app.post('/api/history', (req: Request, res: Response) => {
  const { userID, movieID, lastPosition } = req.body;

  const historyRecord: HistoryRecord = {
    userID,
    movieID,
    lastPosition,
    timestamp: new Date(),
  };

  historyDatabase.push(historyRecord);

  res.json({ message: '시청기록 저장에 성공했어요' });
});

app.listen(port, () => {
  console.log(`아마 서버 작동중일듯합니다 ${port}`);
});