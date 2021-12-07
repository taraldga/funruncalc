export const formatSecondsToMMSS = (seconds: number) => {
    const minutes = Math.floor(seconds/60).toString().padStart(2, "0");
    const printSeconds = (seconds % 60).toString().padStart(2, "0");
    return `${minutes}:${printSeconds}`
}