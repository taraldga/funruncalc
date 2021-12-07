import { FC } from 'react';
import Workout from '../types/Workout';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';

import ArrowDropDown from '@mui/icons-material/ArrowDropDown';
import { formatSecondsToMMSS } from '../utils/formatting';



export interface WorkoutTableProps {
    workout: Workout;
}

const WorkoutTable: FC<WorkoutTableProps> = ({ workout }) => {

    return (
        <Card sx={{ minWidth: 275 }} variant="outlined">
            <CardContent>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                    {workout.date}
                </Typography>
                <Typography variant="h5" component="div">
                    {workout.title}
                </Typography>
                <Table>
                    <TableBody>
                        <TableRow>
                            <TableCell>Distance</TableCell>
                            <TableCell>{workout.distance}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Time</TableCell>
                            <TableCell>{workout.time}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Pace</TableCell>
                            <TableCell>{formatSecondsToMMSS(workout.avgPace)}</TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>Ascent</TableCell>
                            <TableCell>{workout.totalAscent}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </CardContent>
            <CardActions>
                <Button size="small" startIcon={<ArrowDropDown />}>More Info</Button>
            </CardActions>
        </Card>
    )
}


export default WorkoutTable;

