import { useEffect, useState } from 'react'
import logo from './logo.svg'
import './App.css'
import Workout from './types/Workout'
import WorkoutTable, { WorkoutTableProps } from './components/WorkoutTable'
import Grid from '@mui/material/Grid';


function App() {
  const [workouts, setWorkouts] = useState<Workout[] | undefined>(undefined)

  const fetchData = async () => {
    const data = await fetch("http://localhost:8080/workout");
    const workouts = await data.json();

    setWorkouts(workouts);
  }

  useEffect(() => {
    fetchData()
  }, [])

  return (
    <div className="App">
      <Grid container spacing={2} >

      {workouts !== undefined && (
        workouts.map(workout => (
          <Grid item xs={2}>
            <WorkoutTable workout={workout} />
          </Grid>
        )
      ))}
     </Grid>
    </div>
  )
}

export default App
